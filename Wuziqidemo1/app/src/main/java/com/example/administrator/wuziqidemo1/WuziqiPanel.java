package com.example.administrator.wuziqidemo1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class WuziqiPanel extends View
{
    private int PanelWidth;
    private float LineHeght;
    private int Max_Line = 10;
    private int Max_CountInLine= 5;

    private Paint mPaint = new Paint();

    private Bitmap WhitePiece ;
    private Bitmap BlackPiece ;

    private  float RatioPieceOfLineHeight = 3*1.0f/4;

    private boolean FirstWhite = true;//当这个值为true 白子先手
    private List<Point> WhiteArray = new ArrayList<>();
    private List<Point> BlackArray = new ArrayList<>();

    private boolean GameOver ;
    private boolean WhiteWinner;

    public WuziqiPanel(Context context, AttributeSet attrs) {
        super(context, attrs);

        setBackgroundColor(0x44cc66ff);
        init();
    }
    
    private void init()
    {
        mPaint.setColor(0x88000000);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);

        WhitePiece = BitmapFactory.decodeResource(getResources(), R.drawable.stone_w2);
        BlackPiece = BitmapFactory.decodeResource(getResources(), R.drawable.stone_b1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = Math.min(widthSize, heightSize);

        if(widthMode == MeasureSpec.UNSPECIFIED)
        {
            width = widthSize;
        }
        else if(heightMode == MeasureSpec.UNSPECIFIED)
        {
            width = widthSize;
        }

        setMeasuredDimension(width, width);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        PanelWidth = w;
        LineHeght = PanelWidth *1.0f/Max_Line;

        int PieceWidth =(int)(LineHeght * RatioPieceOfLineHeight);
       WhitePiece = Bitmap.createScaledBitmap(WhitePiece,PieceWidth,PieceWidth,false);
        BlackPiece = Bitmap.createScaledBitmap(BlackPiece,PieceWidth,PieceWidth,false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(GameOver)
        {
            return  false;
        }
        int action = event.getAction();
        if(action == MotionEvent.ACTION_UP) {

            int x = (int) event.getX();
            int y = (int) event.getY();

            Point p = getValidPoint( x, y);//防止棋子重复放在同一个位置
            if(WhiteArray.contains(p) || BlackArray.contains(p))
            {
                return false;
            }
            if(FirstWhite) {
                 WhiteArray.add(p);
            }else
            {
                BlackArray.add(p);
            }
            invalidate();
            FirstWhite = !FirstWhite;//黑白双方互换


           //表明view对用户手势有反应
        }
        return true;
    }

    private Point getValidPoint(int x, int y) {
        return new Point((int)(x/LineHeght), (int)(y/LineHeght));
    }

    @Override
    
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        drawBoard(canvas);
        drawPieces(canvas);
        checkGameOver();

    }

    private void checkGameOver() {
        boolean WhiteWin = checkFiveInLine(WhiteArray);
        boolean BlackWin = checkFiveInLine(BlackArray);

        if(WhiteWin || BlackWin)
        {
            GameOver = true;
            WhiteWinner = WhiteWin;

            String text = WhiteWinner?"白棋胜利":"黑棋胜利";
            Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();

        }


    }

    private boolean checkFiveInLine(List<Point> points) {
        for (Point p : points)
        {
            int x = p.x;
            int y = p.y;

            boolean win = checkHorizontal(x, y ,points);
            if(win) return true;
            win = checkVertical(x, y , points);
            if(win) return true;
            win = checkLeftDiagonal(x, y ,points);
            if(win) return true;
            win = checkRightDiagonal(x, y ,points);
            if(win) return true;
        }


        return false;
    }

    /**
     * 判断x,y位置的棋子是否五个横向一致相连。
     */
    private boolean checkHorizontal(int x, int y, List<Point> points) {
        int count = 1;
        //向左计算相同棋子数量
        for(int i=1 ; i < Max_CountInLine; i++)
        {
            if(points.contains(new Point(x - i , y ))){
                count++;
            }else
            {
                break;
            }
        }
        if(count == Max_CountInLine) return true;
        // 向右计算相同棋子数量
        for(int i=1 ; i < Max_CountInLine; i++)
        {
            if(points.contains(new Point(x + i , y ))){
                count++;
            }else
            {
                break;
            }
        }
        if(count == Max_CountInLine) return true;

        return false;
    }

    private boolean checkVertical(int x, int y, List<Point> points) {
        int count = 1;
        //向上计算相同棋子数量
        for(int i=1 ; i < Max_CountInLine; i++)
        {
            if(points.contains(new Point(x , y - i ))){
                count++;
            }else
            {
                break;
            }
        }
        if(count == Max_CountInLine) return true;
        // 向下计算相同棋子数量
        for(int i=1 ; i < Max_CountInLine; i++)
        {
            if(points.contains(new Point(x , y + i))){
                count++;
            }else
            {
                break;
            }
        }
        if(count == Max_CountInLine) return true;

        return false;
    }

    private boolean checkLeftDiagonal(int x, int y, List<Point> points) {
        int count = 1;
        //向左下计算相同棋子数量
        for(int i=1 ; i < Max_CountInLine; i++)
        {
            if(points.contains(new Point(x - i, y + i ))){
                count++;
            }else
            {
                break;
            }
        }
        if(count == Max_CountInLine) return true;
        //向右上计算相同棋子数量
        for(int i=1 ; i < Max_CountInLine; i++)
        {
            if(points.contains(new Point(x + i, y - i))){
                count++;
            }else
            {
                break;
            }
        }
        if(count == Max_CountInLine) return true;

        return false;
    }

    private boolean checkRightDiagonal(int x, int y, List<Point> points) {
        int count = 1;
        //向左上计算相同棋子数量
        for(int i=1 ; i < Max_CountInLine; i++)
        {
            if(points.contains(new Point(x -i , y - i ))){
                count++;
            }else
            {
                break;
            }
        }
        if(count == Max_CountInLine) return true;
        // 向右下计算相同棋子数量
        for(int i=1 ; i < Max_CountInLine; i++)
        {
            if(points.contains(new Point(x + i , y + i))){
                count++;
            }else
            {
                break;
            }
        }
        if(count == Max_CountInLine) return true;

        return false;
    }
    private void drawPieces(Canvas canvas) {
        for(int i = 0 , n = WhiteArray.size(); i < n ; i++)
        {
            Point WhitePoint = WhiteArray.get(i);
            canvas.drawBitmap(WhitePiece,(WhitePoint.x +(1-RatioPieceOfLineHeight)/2)*LineHeght,
                    (WhitePoint.y + (1-RatioPieceOfLineHeight) /2)*LineHeght,null);

        }
        for(int i = 0 , n =BlackArray.size(); i < n ; i++)
        {
            Point BlackPoint = BlackArray.get(i);
            canvas.drawBitmap(BlackPiece,
                    (BlackPoint.x +(1-RatioPieceOfLineHeight)/2)*LineHeght,
                    (BlackPoint.y + (1-RatioPieceOfLineHeight) /2)*LineHeght,null);

        }
    }

    private void drawBoard(Canvas canvas) {
        int w = PanelWidth ;
        float lineHeight = LineHeght ;

        for(int i=0 ; i<Max_Line ; i++ )
        {
            int startX = (int)(lineHeight/2);
            int endX = (int)(w-lineHeight/2);
            int y =(int)((0.5 + i)*lineHeight);
            canvas.drawLine(startX,y,endX,y,mPaint);
            canvas.drawLine(y,startX,y,endX,mPaint);
        }

    }

    public void restart(){
        WhiteArray.clear();
        BlackArray.clear();
        GameOver = false;
        WhiteWinner = false;
        invalidate();

}

}







